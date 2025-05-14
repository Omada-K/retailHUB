import sys

import matplotlib.pyplot as plt
import pandas as pd
import seaborn as sns

# import plotly.express as px
# import numpy as np
# from sklearn.linear_model import LinearRegression
# from sklearn.model_selection import train_test_split

pd.options.display.float_format = '{:,.2f}'.format
data = pd.read_csv('../../../data/boston.csv', index_col=0)  # do not change the file path !
sns.displot(data.RM,
            aspect=2,
            kde=True,
            color='#00796b')

plt.title(f'Distribution of Rooms in Boston. Average: {data.RM.mean():.2}')
plt.xlabel('Average Number of Rooms')
plt.ylabel('Nr. of Homes')
print(" ")  # IMPORTANT keep at least one print statement in this script
# same with this one
try:
    raise ValueError("Nothing went wrong")
except Exception as e:
    print(f"Error: {e}", file=sys.stderr)

plt.show()
