with open('gradle/libs.versions.toml', 'r') as f:
    lines = f.readlines()

new_lines = []
in_libraries = False

for line in lines:
    if line.strip() == '[libraries]' and in_libraries:
        continue # skip duplicate
    if line.strip() == '[libraries]':
        in_libraries = True
    new_lines.append(line)

with open('gradle/libs.versions.toml', 'w') as f:
    f.writelines(new_lines)
