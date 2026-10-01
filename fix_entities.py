with open('shared/src/commonMain/kotlin/com/twilitmusic/app/data/local/entity/Entities.kt', 'r') as f:
    lines = f.readlines()

new_lines = []
for line in lines:
    if line.strip() == 'import kotlinx.datetime.Clock':
        continue
    new_lines.append(line)

new_lines.insert(1, '\nimport kotlinx.datetime.Clock\n')

with open('shared/src/commonMain/kotlin/com/twilitmusic/app/data/local/entity/Entities.kt', 'w') as f:
    f.writelines(new_lines)
