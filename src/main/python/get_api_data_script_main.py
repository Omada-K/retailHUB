'''
1)Authentication & Token Retrieval – The script connects to Salesforce's OAuth2 authentication endpoint
to obtain an access token using client credentials.
2)Data Request & Retrieval – Using the access token, the script makes an authenticated request to a
REST API endpoint to fetch CSV-formatted customers.
3)Data Processing & Storage – The retrieved CSV customers is processed using Pandas, displayed for verification,
and saved as a local file for further analysis.
'''
# Ignore if you have red lines. try to run it. Packages are installed.
import io

import pandas as pd
## TASK1 use try-except block for api calls(post and/or get) --DONE
import requests

print("Date script starts")
## Παίρνουμε όλα τα δεδομένα από το API
url = "http://egov.dai.uom.gr:5001/customers"

try:
    response = requests.get(url)

    if response.status_code == 200:
        customers = response.json()
        print("all customers was successfully downloaded.")
        print(customers[:2])  ## δείξε τα 2 πρώτα για προεπισκόπηση
    else:
        print(f"API returned an error. Status code: {response.status_code}")

except requests.exceptions.RequestException as e:
    print("Connection or response error from API:", e)

## TASK2 insert multiple print logs(on start on end when a block success), so java can know what is happening
import requests

url = "http://egov.dai.uom.gr:5001/customers"

print("START: Fetching all customers from API")  ## Εκκίνηση block

try:
    response = requests.get(url)

    if response.status_code == 200:
        customers = response.json()
        print("SUCCESS: Data fetched successfully")  ## Επιτυχία
        print(customers[:2])  ## Εκτύπωσε μόνο τα πρώτα 2 για προεπισκόπηση προαιρετικά
    else:
        print(f"ERROR: API returned status code {response.status_code}")  # Αν δεν είναι 200

except requests.exceptions.RequestException as e:
    print("ERROR: Something went wrong while calling the API:", e)  ## Σφάλμα σύνδεσης ή αιτήματος

finally:
    print("END: Finished API call block")  ##Τέλος block — είτε πέτυχε είτε όχι

## TASK3 remain apis to something that describes there function not second_api
## TASK4 CHANGE OUTPUT DIRECTORY
##API requirements see API documentation
api_url = "https://login.salesforce.com/services/oauth2/token"
client_id = "3MVG9dAEux2v1sLvxtmY6Z_yz1e8.CJKTAx0varvs97mY_jMn_TaXhdOXnw13wSDg7t3PO0xSEIuMCpAnTksN"
client_secret = "6BDB78C42DF09D218888040D8F643BD4E3E3F8E020E318F0EAB7D157D24A070B"
username = "CAPSTONE@UOM.integration"
password = "capstone@2025ul2MnLZyLm9yeJ4hHk5eOHUGh"

###dictonary parameters
# the header is for authentication
header_dic = {
    "Content-Type": "application/x-www-form-urlencoded"
}
parameters = {
    "grant_type": "password",
    "client_id": client_id,
    "client_secret": client_secret,
    "username": username,
    "password": password
}

## from requests library we are using .post method to get our customers
try:
    response = requests.get(url)

    if response.status_code == 200:
        customers = response.json()
        print("all customers was successfully downloaded.")
        print(customers[:2])  ## δείξε τα 2 πρώτα για προεπισκόπηση
    else:
        print("API returned an error. Status code: {response.status_code}")

except requests.exceptions.RequestException as e:
    print("Connection or response error from API:", e)
response = requests.post(url=api_url, params=parameters,
                         headers=header_dic)  # post method sends our customers to get some response in our case we get the authentication customers

response.raise_for_status()  # ignore - checks for success response
customers = response.json()  # saves customers to json in the memory

# takes a specific customers from the json customers
access_token = customers['access_token']  # takes the authentication token from the first API
instance_url = customers['instance_url']  # takes the URL to access the next API

###second api
second_api_URL = f"{instance_url}/services/apexrest/capstone/"  # using url from the first API

# same
second_api_header = {
    "Authorization": f"Bearer {access_token}", "Content-Type": "Application/json"
}

try:
    second_response = requests.post(url=second_api_URL, headers=second_api_header)

    if second_response.status_code == 200:
        df = pd.read_csv(io.StringIO(second_response.text))
        print("Salesforce customers was successfully downloaded.")
        print(df.head(2))  ## δείξε τα 2 πρώτα για προεπισκόπηση
    else:
        print("Salesforce API returned an error. Status code: {second_response.status_code}")

except requests.exceptions.RequestException as e:
    print("Connection or response error from Salesforce customers API:", e)
second_response = requests.post(url=second_api_URL, headers=second_api_header)  # post method from request
second_response.raise_for_status()  # ignore

df = pd.read_csv(io.StringIO(
    second_response.text))  # StringIO:creates an in-memory file-like object from that string - pandas reads the CSV
print(df)  # prints the customers for validation

df.to_csv("../../../data/raw_data_salesforce.csv")  # creates a csv file with our customers

print("Data script is done !")
