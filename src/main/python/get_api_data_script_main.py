'''
1)Authentication & Token Retrieval – The script connects to Salesforce's OAuth2 authentication endpoint
to obtain an access token using client credentials.
2)Data Request & Retrieval – Using the access token, the script makes an authenticated request to a
REST API endpoint to fetch CSV-formatted data.
3)Data Processing & Storage – The retrieved CSV data is processed using Pandas, displayed for verification,
and saved as a local file for further analysis.
'''
# Ignore if you have red lines. try to run it. Packages are installed.
import io
import pandas as pd
import requests

## TASK1 use try-except block for api calls(post and/or get) --DONE
import requests
print("Date script starts")
## Παίρνουμε όλα τα δεδομένα από το API
url = "http://egov.dai.uom.gr:5001/data"

try:
    response = requests.get(url)

    if response.status_code == 200:
        data = response.json()
        print("all data was successfully downloaded.")
        print(data[:2])  ## δείξε τα 2 πρώτα για προεπισκόπηση
    else:
        print("API returned an error. Status code: {response.status_code}")

except requests.exceptions.RequestException as e:
    print("Connection or response error from API:", e)

## TASK2 insert multiple print logs(on start on end when a block success), so java can know what is happening
import requests

url = "http://egov.dai.uom.gr:5001/data"

print("START: Fetching all data from API")  ## Εκκίνηση block

try:
    response = requests.get(url)

    if response.status_code == 200:
        data = response.json()
        print("SUCCESS: Data fetched successfully")  ## Επιτυχία
        print(data[:2])  ## Εκτύπωσε μόνο τα πρώτα 2 για προεπισκόπηση προαιρετικά
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

## from requests library we are using .post method to get our data
response = requests.post(url=api_url, params=parameters,
                         headers=header_dic)  # post method sends our data to get some response in our case we get the authentication data
response.raise_for_status()  # ignore - checks for success response
data = response.json()  # saves data to json in the memory

# takes a specific data from the json data
access_token = data['access_token']  # takes the authentication token from the first API
instance_url = data['instance_url']  # takes the URL to access the next API

###second api
second_api_URL = f"{instance_url}/services/apexrest/capstone/"  # using url from the first API

# same
second_api_header = {
    "Authorization": f"Bearer {access_token}",
    "Content-Type": "Application/json"
}

second_response = requests.post(url=second_api_URL, headers=second_api_header)  # post method from request
second_response.raise_for_status()  # ignore

df = pd.read_csv(io.StringIO(
    second_response.text))  # StringIO:creates an in-memory file-like object from that string - pandas reads the CSV
print(df)  # prints the data for validation

df.to_csv("../../../salvage/raw_data_salesforce.csv")  # creates a csv file with our data

print("Data script is done !")