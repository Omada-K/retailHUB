import pandas as pd
import matplotlib.pyplot as plt
import seaborn as sns
# import plotly.express as px
# import numpy as np
# from sklearn.linear_model import LinearRegression
# from sklearn.model_selection import train_test_split

pd.options.display.float_format = '{:,.2f}'.format
data = pd.read_csv('data/boston.csv', index_col=0) #do not change the file path !

sns.displot(data.DIS,
            bins=50,
            aspect=2,
            kde=True,
            color='darkblue')

plt.title(f'Distance to Employment Centres. Average: {(data.DIS.mean()):.2}')
plt.xlabel('Weighted Distance to 5 Boston Employment Centres')
plt.ylabel('Nr. of Homes')

plt.show()