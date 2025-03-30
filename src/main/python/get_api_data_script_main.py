import requests
import io
import pandas as pd

api_url =  "https://login.salesforce.com/services/oauth2/token"
client_id = "3MVG9dAEux2v1sLvxtmY6Z_yz1e8.CJKTAx0varvs97mY_jMn_TaXhdOXnw13wSDg7t3PO0xSEIuMCpAnTksN"
client_secret = "6BDB78C42DF09D218888040D8F643BD4E3E3F8E020E318F0EAB7D157D24A070B"
username = "CAPSTONE@UOM.integration"
password = "capstone@2025ul2MnLZyLm9yeJ4hHk5eOHUGh"
##parameters
header_dic = {
    "Content-Type":"application/x-www-form-urlencoded"
}
parameters = {
    "grant_type": "password",
    "client_id": client_id,
    "client_secret": client_secret,
    "username":username,
    "password":password
}

#body
response = requests.post(url=api_url,params=parameters,headers=header_dic)
response.raise_for_status() #ignore - check for success response
data = response.json()

access_token = data['access_token']
instance_url = data['instance_url']

###second api
second_api_URL = f"{instance_url}/services/apexrest/capstone/"
##Bearer and access_token NEEDS A SPACE between !!@@
second_api_header = {
    "Authorization":f"Bearer {access_token}",
    "Content-Type":"Application/json"
}

second_response = requests.post(url=second_api_URL,headers=second_api_header)
second_response.raise_for_status()

df = pd.read_csv(io.StringIO(second_response.text))
print(df)


df.to_csv("raw_data_salesforce.csv") #works fine


