import pandas as pd
import matplotlib          #First import base module
matplotlib.use("Agg")      #Then set backend
import matplotlib.pyplot as plt  #Then import pyplot
import seaborn as sns
from sklearn.tree import DecisionTreeClassifier
from sklearn.model_selection import train_test_split
from sklearn.metrics import classification_report

# Load data files
orders = pd.read_csv(r"C:\CAPSTONE\retailHUB\data\orders_final_faked.csv", delimiter=';')
customers = pd.read_csv(r"C:\CAPSTONE\retailHUB\data\customers_final_faked.csv", delimiter=',')


#  Calculate total amount per order
orders["Total Amount"] = orders["Quantity"] * orders["Price"]

#  Parse dates and extract month
orders["Created At"] = pd.to_datetime(orders["Created At"], format="%d/%m/%Y")
orders["Month"] = orders["Created At"].dt.to_period("M")

#  Merge orders with customer data
merged = orders.merge(customers, left_on="Customer", right_on="ID")

#  Create age groups
merged["Age Group"] = pd.cut(
    merged["Age"],
    bins=[17, 25, 35, 45, 55, 70],
labels=["18-25", "26-35", "36-45", "46-55", "56+"]
)

#  Create and save boxplot: Total Amount by Age Group
sns.boxplot(data=merged, x="Age Group", y="Total Amount")
plt.title("Total Amount by Age Group")
plt.tight_layout()
plt.savefig(r"C:\CAPSTONE\retailHUB\data\boxplot_by_age.png")
plt.clf()

#  Identify high-value customers (total amount > 1000)
customer_total = merged.groupby("Customer")["Total Amount"].sum().reset_index()
customer_total["High_Value"] = (customer_total["Total Amount"] > 1000).astype(int)

#  Merge total amounts back to customer data
features = customers.merge(customer_total, left_on="ID", right_on="Customer")

#  Select features and labels for classification
X = features[["Age"]]
y = features["High_Value"]

#  Train/test split and classifier
X_train, X_test, y_train, y_test = train_test_split(X, y, test_size=0.3, random_state=42)
clf = DecisionTreeClassifier()
clf.fit(X_train, y_train)
y_pred = clf.predict(X_test)

#  Print classification report
print(classification_report(y_test, y_pred))

# Bar Chart : Classification Metrics
report = classification_report(y_test, y_pred, output_dict=True)
df = pd.DataFrame(report).T.loc[["0", "1"], ["precision", "recall", "f1-score"]]
df.index = ["Low Value", "High Value"]

df.plot(kind="bar", figsize=(8, 5), colormap="coolwarm")
plt.title("Classification Metrics per Class")
plt.ylabel("Score")
plt.ylim(0, 1)
plt.xticks(rotation=0)
plt.tight_layout()
plt.savefig(r"C:\CAPSTONE\retailHUB\data\classification_metrics.png")
plt.show()
