import os
import pandas as pd

# Get the path of the current script
base_dir = os.path.dirname(os.path.abspath(__file__))

# Define the data folder and create it if it doesn't exist
data_dir = os.path.normpath(os.path.join(base_dir, "..", "..", "data"))
os.makedirs(data_dir, exist_ok=True)

# Full path to input raw CSV
input_path = os.path.join(data_dir, "C:/CapStone_Project/retailHUB/src/main/data/raw_data_salesforce.csv")

# Load the raw CSV data
df = pd.read_csv(input_path)

# Drop unnamed column if it exists
df = df.drop(columns=["Unnamed: 0"], errors='ignore')

# Remove rows with missing values in key columns
df = df.dropna(subset=["Customer ID", "Product Category", "Quantity", "Price per Unit"])

# Convert to numeric and drop invalid entries
df["Quantity"] = pd.to_numeric(df["Quantity"], errors='coerce')
df["Price per Unit"] = pd.to_numeric(df["Price per Unit"], errors='coerce')
df = df.dropna(subset=["Quantity", "Price per Unit"])

# Ensure all quantities and prices are positive
df = df[(df["Quantity"]>0) & (df["Price per Unit"]>0)]

# Now create the Total Amount column
df["Total Amount"] = df["Quantity"] * df["Price per Unit"]

# Keep rows where Age is missing or within valid range
df = df[(df["Age"].isna()) | ((df["Age"] >= 10) & (df["Age"] <= 100))]

# Convert Date column if present
if "Date" in df.columns:
    # You can specify the format if dates are consistent, e.g. format="%d/%m/%Y"
    df["Date"] = pd.to_datetime(df["Date"], dayfirst=True, errors="coerce")
    df["Date"] = df["Date"].dt.date
    df = df.dropna(subset=["Date"])

# Reset index
df = df.reset_index(drop=True)

# Save cleaned data to CSV & Excel File
df.to_csv(os.path.join(data_dir, "C:/CapStone_Project/retailHUB/src/main/data/cleaned_salesforce_data.csv"), index=False)
df.to_excel(os.path.join(data_dir, "C:/CapStone_Project/cleaned_salesforce_data.xlsx"), index=False)

# Print first 5 rows
print(df.head())
print(df["Date"])