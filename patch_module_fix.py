import sys

with open('app/src/main/java/com/twilitmusic/app/di/DatabaseModule.kt', 'r') as f:
    content = f.read()

content = content.replace('}\n\n    @Provides\n    fun provideQueueDao(database: TwilitDatabase): QueueDao = database.queueDao()', '\n    @Provides\n    fun provideQueueDao(database: TwilitDatabase): QueueDao = database.queueDao()\n}')

with open('app/src/main/java/com/twilitmusic/app/di/DatabaseModule.kt', 'w') as f:
    f.write(content)
