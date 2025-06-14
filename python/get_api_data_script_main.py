import io
import pandas as pd
import requests
from pathlib import Path

# === Configuration ===
api_url = "https://login.salesforce.com/services/oauth2/token"
client_id = "3MVG9dAEux2v1sLvxtmY6Z_yz1e8.CJKTAx0varvs97mY_jMn_TaXhdOXnw13wSDg7t3PO0xSEIuMCpAnTksN"
client_secret = "6BDB78C42DF09D218888040D8F643BD4E3E3F8E020E318F0EAB7D157D24A070B"
username = "CAPSTONE@UOM.integration"
password = "capstone@2025ul2MnLZyLm9yeJ4hHk5eOHUGh"

# === Header kai params apo to token ===
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

# === Checkare ta routes ===
base_dir = Path(__file__).resolve().parent.parent
data_dir = base_dir / "data"
data_dir.mkdir(parents=True, exist_ok=True)
output_path = data_dir / "raw_data_salesforce.csv"

# === Request Token ===
try:
    response = requests.post(url=api_url, params=parameters, headers=header_dic)
    response.raise_for_status()
    customers = response.json()
    access_token = customers['access_token']
    instance_url = customers['instance_url']
except Exception as e:
    print("Error retrieving access token:", e)
    exit(1)

# === Request Data ===
second_api_URL = f"{instance_url}/services/apexrest/capstone/"
second_api_header = {
    "Authorization": f"Bearer {access_token}",
    "Content-Type": "application/json"
}

try:
    second_response = requests.post(url=second_api_URL, headers=second_api_header)
    second_response.raise_for_status()
    df = pd.read_csv(io.StringIO(second_response.text))
    print("Salesforce data successfully downloaded.")
except Exception as e:
    print("Error fetching or parsing customer data:", e)
    exit(1)

# === apothikeusi se CSV ===
try:
    df.to_csv(output_path, index=False)
    print(f"Data saved to: {output_path}")
except Exception as e:
    print(f"Failed to save data: {e}")

print("Data fetching script is done!")
