import datetime
import mysql.connector
from weasyprint import HTML
import inspect, os.path

def connect():
    """ Connect to MySQL database """
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


def main():
    conn = connect()
    cursor = conn.cursor()
    cursor.execute("SELECT * FROM SpareParts")

    entries = []
    for row in cursor:
        entries.append(row)

    mid = generateRow(entries)

    cpath = os.path.dirname(__file__)

    fname = datetime.date.today()

    with open(f"{cpath}/report.html", "w") as f:
        f.write(head+mid+tail)

    HTML('report.html').write_pdf(f'{cpath}/{fname}-report.pdf')


if __name__ == "__main__":
    main()