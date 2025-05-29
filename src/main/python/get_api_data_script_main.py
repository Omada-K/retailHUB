'''
1)Authentication & Token Retrieval – The script connects to Salesforce's OAuth2 authentication endpoint
to obtain an access token using client credentials.
2)Data Request & Retrieval – Using the access token, the script makes an authenticated request to a
REST API endpoint to fetch CSV-formatted customers.
3)Data Processing & Storage – The retrieved CSV customers is processed using Pandas, displayed for verification,
and saved as a local file for further analysis.
'''
import io
import requests
import pandas as pd
import os

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
    response = requests.post(url=api_url, params=parameters,
                             headers=header_dic)  # post method sends our customers to get some response in our case we get the authentication customers

except:
    print("An error occurred while trying to connect to the first API")
else:
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

    except requests.exceptions.RequestException as e:
        print("Connection or response error from Salesforce customers API:", e)
    second_response = requests.post(url=second_api_URL, headers=second_api_header)  # post method from request
    second_response.raise_for_status()  # ignore

    df = pd.read_csv(io.StringIO(
        second_response.text))  # StringIO:creates an in-memory file-like object from that string - pandas reads the CSV
    print(df)  # prints the customers for validation
    try:
        df.to_csv("../../../data/raw_data_salesforce.csv")  # creates a csv file with our customers
    except OSError:
        print("Error: Cannot save file into a non-existent directory")
        print("Creating a directory")
        print("Trying again")
        try:
            os.mkdir("../../../data")
            df.to_csv("../../../data/raw_data_salesforce.csv")
        except Exception as e:
            print(f"An error occurred: {e}")





    print("Data script is done !")
