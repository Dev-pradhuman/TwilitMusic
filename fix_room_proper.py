import os
import re

db_path = 'shared/src/commonMain/kotlin/com/twilitmusic/app/data/local/TwilitDatabase.kt'
with open(db_path, 'r') as f:
    content = f.read()

# Remove SupportSQLiteDatabase and Migration
content = re.sub(r'import androidx\.sqlite\.db\.SupportSQLiteDatabase\n', '', content)
content = re.sub(r'import androidx\.room\.migration\.Migration\n', '', content)

# Add ConstructedBy
content = content.replace('import androidx.room.RoomDatabase', 'import androidx.room.RoomDatabase\nimport androidx.room.ConstructedBy\nimport androidx.room.RoomDatabaseConstructor')
content = content.replace('abstract class TwilitDatabase', '@ConstructedBy(TwilitDatabaseConstructor::class)\nabstract class TwilitDatabase')

content += '\n\n@Suppress("NO_ACTUAL_FOR_EXPECT")\nexpect object TwilitDatabaseConstructor : RoomDatabaseConstructor<TwilitDatabase>\n'

with open(db_path, 'w') as f:
    f.write(content)
