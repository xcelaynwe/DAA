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
        n_val = parts[2]
        time_val = parts[3] + '.' + parts[4] if len(parts) == 7 else parts[3]
        data.append([algo, inp, n_val, time_val])

df = pd.DataFrame(data, columns=['algorithm', 'input', 'n', 'time_ms'])
df['n'] = pd.to_numeric(df['n'], errors='coerce')
df['time_ms'] = pd.to_numeric(df['time_ms'], errors='coerce')
df = df.dropna()

plt.figure(figsize=(10, 6))

for algo in df['algorithm'].unique():
    for inp in df['input'].unique():
        subset = df[(df['algorithm'] == algo) & (df['input'] == inp)].sort_values('n')
        if not subset.empty:
            plt.plot(subset['n'], subset['time_ms'], marker='o', label=f'{algo} - {inp}')

plt.xscale('log')
plt.yscale('log')
plt.xlabel('Array Size n (log scale)')
plt.ylabel('Execution Time ms (log scale)')
plt.title('Algorithm Performance Benchmark (9 Configurations)')
plt.legend(bbox_to_anchor=(1.05, 1), loc='upper left')
plt.grid(True, which="both", ls="--")
plt.tight_layout()

plt.savefig('execution_time_plot.png', dpi=300)
plt.show()