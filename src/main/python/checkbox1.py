import pandas as pd
import matplotlib.pyplot as plt
import seaborn as sns
# import plotly.express as px
# import numpy as np
# from sklearn.linear_model import LinearRegression
# from sklearn.model_selection import train_test_split

pd.options.display.float_format = '{:,.2f}'.format
data = pd.read_csv('data/boston.csv', index_col=0) #do not change the file path !

sns.displot(data['PRICE'],
            bins=50,
            aspect=2,
            kde=True,
            color='#2196f3')

plt.title(f'1970s Home Values in Boston. Average: ${(1000*data.PRICE.mean()):.6}')
plt.xlabel('Price in 2000s')
plt.ylabel('Nr. of Homes')

plt.show()
