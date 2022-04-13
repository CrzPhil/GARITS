#
# Program that takes a jobID as input and generates a full report image + pdf.
# jobID has to be passed as system argument 
#
# Output is in current directory.

import datetime
import mysql.connector
from weasyprint import HTML
import os.path
import fitz
from typing import Tuple
import sys

head = """
<html>

<head>
    <title>Customer Invoice</title>
    <style>
         :root {
            font-size: small;
        }
        
        table,
        th,
        td {
            border-collapse: collapse;
            padding: 20px;
            padding-left: 100px;
        }
        
        th {
            border-bottom: 1px solid black;
        }
        
        td {
            padding: 5px;
            padding-left: 100px;
        }
    </style>
</head>

<body style="margin-left: 50px;">
"""

tail = """
    <br><br><br><br>

    <div class="tail">
        Thank you for your valued custom. We look forward to receiving your payment in due course.
        <br><br><br><br><br> Yours sincerely,
        <br><br><br><br>G. Lancaster
    </div>

</body>

</html>
"""

def connect():
    conn = None
    try:
        conn = mysql.connector.connect(host='176.58.124.119',
                                       database='GARITS',
                                       user='GARITS',
                                       password='G@R!T$$$')
        if conn.is_connected():
            print('Connected to MySQL database')

    except Exception as e:
        print(e)

    return conn

# This function is taken from: https://www.thepythoncode.com/article/convert-pdf-files-to-images-in-python
def convert_pdf2img(input_file: str, pages: Tuple = None):
    """Converts pdf to image and generates a file by page"""
    # Open the document
    pdfIn = fitz.open(input_file)
    output_files = []
    # Iterate throughout the pages
    for pg in range(pdfIn.pageCount):
        if str(pages) != str(None):
            if str(pg) not in str(pages):
                continue
        # Select a page
        page = pdfIn[pg]
        rotate = int(0)
        # PDF Page is converted into a whole picture 1056*816 and then for each picture a screenshot is taken.
        # zoom = 1.33333333 -----> Image size = 1056*816
        # zoom = 2 ---> 2 * Default Resolution (text is clear, image text is hard to read)    = filesize small / Image size = 1584*1224
        # zoom = 4 ---> 4 * Default Resolution (text is clear, image text is barely readable) = filesize large
        # zoom = 8 ---> 8 * Default Resolution (text is clear, image text is readable) = filesize large
        zoom_x = 2
        zoom_y = 2
        # The zoom factor is equal to 2 in order to make text clear
        # Pre-rotate is to rotate if needed.
        mat = fitz.Matrix(zoom_x, zoom_y).preRotate(rotate)
        pix = page.getPixmap(matrix=mat, alpha=False)
        output_file = f"{os.path.dirname(__file__)}/preview_page{pg+1}.png"
        pix.writePNG(output_file)
        output_files.append(output_file)
    pdfIn.close()
    summary = {
        "File": input_file, "Pages": str(pages), "Output File(s)": str(output_files)
    }
    # Printing Summary
    #print("## Summary ########################################################")
    #print("\n".join("{}:{}".format(i, j) for i, j in summary.items()))
    #print("###################################################################")
    return output_files

def generateAddress(address):
    # Split address into sub-parts like Street, Area code, etc.
    splitAddr = address.split(', ')
    head = """
            <div class="addresses">
        <div class="customeraddr" style="float: left; margin-top: 20px;">
    """

    tmp = """"""
    
    for i in splitAddr:
        tmp += f"{i}, <br>"

    # This doesn't change, it's the garage's address details.
    tail = """
        </div>
        <div class="address" style="float: right; margin-right: 50px; margin-top: 20px;">
            Quick Fix Fitters, <br>19 High St., <br>Ashford, <br>Kent CT16 8YY <br><br>18th October 2021
        </div>
    </div>"""

    return head+tmp+tail

def generateName(name):
    return f"""
        <br><br><br><br><br><br><br><br>
    <div class="clientName">
        Dear {name},
    </div>"""

def generateInvoiceNo(no):
    return f"""
        <div class="invoiceno" style="text-align: center;">
        <b>INVOICE NO.: {no}</b>
    </div>"""

def generateVehicleDetails(regNo, make, model):
    return f"""    <div class="vehicle-info" style="margin-top: 50px;">
        Vehicle Registration No.: {regNo}
        <br> Make: {make}
        <br> Model: {model}
    </div>"""

def generateDetails(jobDetails):
    head = """
    <br> Description of work: <br>
    <div class="descr">
        <ol type="1">
    """

    tmp = """"""
    parsedDetails = jobDetails.split(", ")
    for detail in parsedDetails:
        tmp += f"<li>{detail}</li>" 
    tail = """
        </ol>
    </div>
    <br>"""
    return head+tmp+tail

def generateTable(parts):
    head = """
            <div class="table">
        <table>
                    <tr>
                <th>Item</th>
                <th>Part No.</th>
                <th>Cost (£)</th>
            </tr>"""
    mid = """"""
    for part in parts:
        mid += f"""
                    <tr>
                <td>{part[0]}</td>
                <td>{part[1]}</td>
                <td>{part[2]}</td>
            </tr>"""
    tail = """
                    <tr>
                <td>
                    <td>
                        <td></td>
                    </td>
                </td>
            </tr>
            <tr>
                <td>
                    <td>
                        <td></td>
                    </td>
                </td>
            </tr>"""
    return head+mid+tail

def generateLabour(rate, duration):
    total = float(rate) * float(duration)
    tail = f"""            <tr>
                <td>Labour ({duration} Hours)</td>
                <td>{rate}</td>
                <td>{total}</td>
            </tr>
"""
    return tail

def generateTotal(parts, labour):
    total = labour
    for part in parts:
        total += float(part[2])
    return f"""
     <tr>
                     <td>Total</td>
                <td></td>
                <td>{total}</td>

            </tr>
             <tr>
                 <td>VAT</td>
                <td></td>
                <td>{total*0.2}</td>
            </tr>
            <tr style="border-bottom: 1px solid black;">
                <td><b>Grand Total</b></td>
                <td></td>
                <td><b>{total + total*0.2}</b></td>
            </tr>
                    </table>
    </div>"""
    
def makeInvoice(jobID, head, addr, name, invoiceno, vehicDet, jobDet, tableDet, labourDet, total, tail):
    # Get directory of this file, to keep all generated files here
    cpath = os.path.dirname(__file__)

    # Set filename to job ID
    fname = jobID

    # Write HTML 
    with open(f"{cpath}/invoice.html", "w") as f:
        f.write(head+addr+name+invoiceno+vehicDet+jobDet+tableDet+labourDet+total+tail)
    
    # Create PDF from HTML
    HTML(f'{cpath}/invoice.html').write_pdf(f'{cpath}/{fname}-invoice.pdf')


    # Create preview image for JPanel, as PDFs don't seem to be working properly
    convert_pdf2img(f"{cpath}/{fname}-invoice.pdf")

def main(jobID):
    conn = connect()
    cursor = conn.cursor()

    # Get Customer details + Vehicle details
    #query = f"SELECT CS.firstName, CS.lastName, CS.address, CS.discount, Vehicles.registrationNo, Vehicles.make, Vehicles.model, CJ.additionalInfo FROM Customers CS \
    #    INNER JOIN Vehicles ON CS.customerID = Vehicles.CustomercustomerID \
    #    INNER JOIN Jobs CJ on Vehicles.registrationNo = CJ.registrationNo WHERE CJ.jobID = {jobID} AND CJ.status = 1"
    
    query = f"SELECT C.firstName, C.lastName, C.address, C.discount, V.registrationNo, V.make, V.model, Jobs.additionalInfo FROM Jobs INNER JOIN Vehicles V on Jobs.registrationNo = V.registrationNo INNER JOIN Customers C on V.CustomercustomerID = C.customerID WHERE jobID = {jobID}"
    
    cursor.execute(query)
    
    # We should get back:
    # FirstName | LastName | Address | Discount | RegNo | Make | Model | jobDetails

    info = []

    for row in cursor:
        info.append(row)
    
    # If the query is wrong, debug and self destruct.
    if len(info) != 1:
        print("TOO MUCH INFO RETURNED FROM FIRST QUERY")
        print(query)
        sys.exit(0)

    addr = generateAddress(info[0][2])
    name = generateName(info[0][1]+" "+info[0][1])
    invoiceNo = generateInvoiceNo(jobID)
    vehicleDetails = generateVehicleDetails(info[0][4],info[0][5],info[0][6])
    jobDetails = generateDetails(info[0][7])

#    print(head)
#    print(addr)
#    print(name)
#    print(invoiceNo)
#    print(vehicleDetails)
#    print(jobDetails)

    # Get parts used on the job
    query = f"SELECT SpareParts.partName, SpareParts.code, SpareParts.price FROM SpareParts \
        INNER JOIN Job_SpareParts JSP on SpareParts.code = JSP.partCode WHERE jobID = {jobID}"

    cursor.execute(query)

    # Returns
    # PartName | Code | Price

    parts = []

    for row in cursor:
        parts.append(row)


    # Get Mechanic Rate + Job duration
    query = f"SELECT Staff.hourlyRate, Jobs.duration FROM Staff INNER JOIN Jobs ON Staff.staffID = Jobs.mechanicID WHERE jobID = {jobID};"

    cursor.execute(query)

    result = []

    for row in cursor:
        result.append(row)

    if len(result) > 1:
        print("QUERY RETURNED MORE THAN ONE ROW!")
        print(query)
        sys.exit(0)
    
    rate = result[0][0]
    duration = result[0][1]

    table = generateTable(parts)
    labour = generateLabour(rate, duration)
    total = generateTotal(parts, int(rate)*float(duration))

#    print(table)
#    print(labour)
#    print(total)
#    print(tail)

    makeInvoice(jobID, head, addr, name, invoiceNo, vehicleDetails, jobDetails, table, labour, total, tail)
    
    # clean up html file
    os.remove(f"{os.path.dirname(__file__)}/invoice.html")


if __name__ == "__main__":
    if len(sys.argv) == 1:
        print(f"Usage: {sys.argv[0]} jobID \n jobID: ID of a completed job in the GARITS DB.")
    else:
        main(sys.argv[1])