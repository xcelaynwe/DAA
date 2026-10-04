import pandas as pd
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
        depth = parts[-1]
        data.append([algo, inp, n, depth])

df = pd.DataFrame(data, columns=['algorithm', 'input', 'n', 'max_depth'])
df['n'] = pd.to_numeric(df['n'], errors='coerce')
df['max_depth'] = pd.to_numeric(df['max_depth'], errors='coerce')
df = df.dropna()

plt.figure(figsize=(10, 6))

for algo in df['algorithm'].unique():
    for inp in df['input'].unique():
        subset = df[(df['algorithm'] == algo) & (df['input'] == inp)].sort_values('n')
        if not subset.empty:
            plt.plot(subset['n'], subset['max_depth'], marker='o', label=f'{algo} - {inp}')

plt.xscale('log')
plt.xlabel('n (Log Scale)')
plt.ylabel('Max Recursion Depth')
plt.title('Max Recursion Depth vs n')
plt.legend(bbox_to_anchor=(1.05, 1), loc='upper left')
plt.grid(True, which="both", ls="--")
plt.tight_layout()

plt.savefig('recursion_depth_plot.png', dpi=300)
plt.show()