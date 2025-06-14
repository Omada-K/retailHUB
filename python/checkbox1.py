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
output_path = base_dir / "data" / "boston1.png"

data = pd.read_csv(file_path, index_col=0)  # do not change the file path !

sns.displot(data['PRICE'],
            bins=50,
            aspect=2,
            kde=True,
            color='#2196f3')

plt.title(f'1970s Home Values in Boston. Average: ${(1000 * data.PRICE.mean()):.6}')
plt.xlabel('Price in 2000s')
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
# plt.show()
