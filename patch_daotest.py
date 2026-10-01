import sys

with open('app/src/test/java/com/twilitmusic/app/data/local/DaoTest.kt', 'r') as f:
    content = f.read()

content = content.replace('playHistoryDao.insertPlayHistory(history)', 'playHistoryDao.addPlayHistory(history)')
content = content.replace('playHistoryDao.getRecentHistory(10)', 'playHistoryDao.getPlayHistory(10)')

with open('app/src/test/java/com/twilitmusic/app/data/local/DaoTest.kt', 'w') as f:
    f.write(content)
