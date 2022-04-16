import datetime
import mysql.connector
from weasyprint import HTML
import os.path
import fitz
from typing import Tuple


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


def connect():
    """ Connect to MySQL database """
    conn = None
    try:
        conn = mysql.connector.connect(host='REDACTED',
                                       database='REDACTED',
                                       user='REDACTED',
                                       password='REDACTED')
        if conn.is_connected():
            print('Connected to MySQL database')

    except Exception as e:
        print(e)

    return conn
    

class SparePart:
    def __init__(self, code, name, manuf, type, year, price, stock ) -> None:
        self.code = code
        self.name = name
        self.manuf = manuf
        self.type = type
        self.year = year
        self.price = price
        self.stock = stock

head = """
<html>

<head>
    <title>Stock Report</title>
    <style>
        @page {
            size: A4;
            margin: 1cm;
        }

        table,
        th,
        td {
            border: 1px solid black;
            border-collapse: collapse;
        }
        
        td {
            padding: 5px;
        }
    </style>
</head>

<body>
    <div class="address">
        Quick Fix Fitters, <br>19 High St., <br>Ashford, <br>Kent CT16 8YY
    </div>
    <br>
    <div class="title">
        <b>Spare Parts / Stock Level Report</b>
    </div>
    <br>
    <div class="period">
        Current Stock:
    </div>
    <br>
    <div class="table">
        <table>
            <tr style="writing-mode: sideways-lr;">
                <th>Part Name</th>
                <th>Code</th>
                <th>Manufacturer</th>
                <th>Vehicle Type</th>
                <th>Year(s)</th>
                <th>Price</th>
                <th>Stock Level</th>
            </tr>
"""

tail = f"""
        </table>
    </div>
    <br>
    <div class="signature">
        Report Date: {datetime.date.today()}
        <br><br> Senior Storekeeper:
        <br><br> Ms E. Kournikova
    </div>
</body>

</html>
"""

def generateRow(parts):
    out = f""""""

    for part in parts:
        out += f"""<tr>
                    <td>{part[0]}</td>
                    <td>{part[1]}</td>
                    <td>{part[2]}</td>
                    <td>{part[3]}</td>
                    <td>{part[4]}</td>
                    <td>£{part[6]}</td>
                    <td>{part[5]}</td>
                </tr>""" 
    return out


def cleanDir(dirname):
    os.remove(f"{dirname}/report.html")


def main():
    conn = connect()
    cursor = conn.cursor()
    cursor.execute("SELECT * FROM SpareParts")
    
    entries = []
    for row in cursor:
        entries.append(row)
    
    # Call our table creating function
    mid = generateRow(entries)

    # Get directory of this file, to keep all generated files here
    cpath = os.path.dirname(__file__)

    # Set filename to today's date
    fname = datetime.date.today()

    # Write HTML 
    with open(f"{cpath}/report.html", "w") as f:
        f.write(head+mid+tail)
    
    # Create PDF from HTML
    HTML(f'{cpath}/report.html').write_pdf(f'{cpath}/{fname}-report.pdf')


    # Create preview image for JPanel, as PDFs don't seem to be working properly
    convert_pdf2img(f"{cpath}/{fname}-report.pdf")

    # Get rid of .html file
    cleanDir(cpath)


if __name__ == "__main__":
    main()