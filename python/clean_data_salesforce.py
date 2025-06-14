import pandas as pd
from pathlib import Path

# Base directory: /python → go one level up to project root
base_dir = Path(__file__).resolve().parent.parent
data_dir = base_dir / "data"

# Ensure data directory exists
data_dir.mkdir(parents=True, exist_ok=True)

# Input path: raw_data_salesforce.csv
input_path = data_dir / "raw_data_salesforce.csv"

# Load raw data
df = pd.read_csv(input_path)

# Drop unnamed column if it exists
df = df.drop(columns=["Unnamed: 0"], errors='ignore')

# Clean and validate data
df = df.dropna(subset=["Customer ID", "Product Category", "Quantity", "Price per Unit"])
df["Quantity"] = pd.to_numeric(df["Quantity"], errors='coerce')
df["Price per Unit"] = pd.to_numeric(df["Price per Unit"], errors='coerce')
df = df.dropna(subset=["Quantity", "Price per Unit"])
df = df[(df["Quantity"] > 0) & (df["Price per Unit"] > 0)]

# Create Total Amount column
df["Total Amount"] = df["Quantity"] * df["Price per Unit"]

# Filter Age
df = df[(df["Age"].isna()) | ((df["Age"] >= 10) & (df["Age"] <= 100))]

# Parse Date column if exists
if "Date" in df.columns:
    df["Date"] = pd.to_datetime(df["Date"], dayfirst=True, errors="coerce")
    df["Date"] = df["Date"].dt.date
    df = df.dropna(subset=["Date"])

# Reset index
df = df.reset_index(drop=True)

# Save to cleaned files (CSV and Excel)
df.to_csv(data_dir / "cleaned_salesforce_data.csv", index=False)
df.to_excel(data_dir / "cleaned_salesforce_data.xlsx", index=False)

# Output sample
print(df.head())
print(df["Date"])
