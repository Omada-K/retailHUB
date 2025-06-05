import pandas as pd
import matplotlib.pyplot as plt
import seaborn as sns
import os

# Load the orders dataset
orders_df = pd.read_csv("C:/CAPSTONE/retailHUB/data/orders_final_cleaned.csv")

# Convert the 'Created At' column to datetime
orders_df['Created At'] = pd.to_datetime(orders_df['Created At'], format="%d/%m/%Y")

# Extract day of week from the 'Created At' column
orders_df['Weekday'] = orders_df['Created At'].dt.day_name()

# Define natural order of weekdays (Monday → Sunday)
weekday_order = ['Monday', 'Tuesday', 'Wednesday', 'Thursday', 'Friday', 'Saturday', 'Sunday']
orders_df['Weekday'] = pd.Categorical(orders_df['Weekday'], categories=weekday_order, ordered=True)

# Create boxplot of sales (Price) per weekday
plt.figure(figsize=(10, 6))
orders_df.boxplot(column='Price', by='Weekday', grid=False)

# Set chart titles and labels
plt.title('Sales Distribution by Weekday')         # Chart title
plt.suptitle('')                                   # Remove the default matplotlib group title
plt.xlabel('Day of the Week')                      # X-axis label
plt.ylabel('Sales Amount (€)')                     # Y-axis label
plt.xticks(rotation=45)                            # Rotate x-axis labels for better readability
plt.tight_layout()                                 # Adjust layout to prevent overlapping


# Save plot to file in the data folder
plt.savefig("C:/CAPSTONE/retailHUB/data/boxplot_by_weekday.png")
