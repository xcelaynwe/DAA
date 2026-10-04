import pandas as pd
import numpy as np
import matplotlib.pyplot as plt

with open('results.csv', 'r', encoding='utf-8') as f:
    lines = f.readlines()

data = []
for line in lines[1:]:
    parts = line.strip().split(',')
    if len(parts) >= 6:
        algo = parts[0]
        inp = parts[1]
        n = parts[2]
        comp = parts[-2]
        data.append([algo, inp, n, comp])

df = pd.DataFrame(data, columns=['algorithm', 'input', 'n', 'comparisons'])
df['n'] = pd.to_numeric(df['n'], errors='coerce')
df['comparisons'] = pd.to_numeric(df['comparisons'], errors='coerce')
df = df.dropna()

df['ratio'] = df['comparisons'] / (df['n'] * np.log2(df['n']))

plt.figure(figsize=(10, 6))

for algo in df['algorithm'].unique():
    for inp in df['input'].unique():
        subset = df[(df['algorithm'] == algo) & (df['input'] == inp)].sort_values('n')
        if not subset.empty:
            plt.plot(subset['n'], subset['ratio'], marker='o', label=f'{algo} - {inp}')

plt.xscale('log')
plt.xlabel('n (Log Scale)')
plt.ylabel('Ratio (Comparisons / Scaling Factor)')
plt.title('Ratio vs n (Verifying Theta Bound)')
plt.legend(bbox_to_anchor=(1.05, 1), loc='upper left')
plt.grid(True, which="both", ls="--")
plt.tight_layout()

plt.savefig('ratio_plot.png', dpi=300)
plt.show()