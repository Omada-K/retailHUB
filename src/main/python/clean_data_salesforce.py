import pandas as pd


# Full path to input raw CSV
input_path =  "../../../data/raw_data_salesforce.csv"
out_put_path_string = "../../../data/cleaned_salesforce_data.csv"

# Load the raw CSV data
try:
    df = pd.read_csv(input_path)
except Exception as e:
    print(f"Error:{e}")
else:


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
    df.to_csv(out_put_path_string)

    # Print first 5 rows
    print(df.head())
    print(df["Date"])