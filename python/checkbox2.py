import matplotlib.pyplot as plt
import pandas as pd
import seaborn as sns
import sys
from pathlib import Path

# import plotly.express as px
# import numpy as np
# from sklearn.linear_model import LinearRegression
# from sklearn.model_selection import train_test_split

pd.options.display.float_format = '{:,.2f}'.format
# paths
base_dir = Path(__file__).resolve().parent.parent
file_path = base_dir / "data" / "boston.csv"
output_path = base_dir / "data" / "boston2.png"

data = pd.read_csv(file_path, index_col=0)

sns.displot(data.DIS,
            bins=50,
            aspect=2,
            kde=True,
            color='darkblue')

plt.title(f'Distance to Employment Centres. Average: {(data.DIS.mean()):.2}')
plt.xlabel('Weighted Distance to 5 Boston Employment Centres')
plt.ylabel('Nr. of Homes')
print(" ")  # IMPORTANT keep at least one print statement in this script
# same with this one
try:
    raise ValueError("Nothing went wrong")
except Exception as e:
    print(f"Error: {e}", file=sys.stderr)

# Make sure the data directory exists (optional safety check)
output_path.parent.mkdir(parents=True, exist_ok=True)
plt.savefig(output_path)
plt.show()
