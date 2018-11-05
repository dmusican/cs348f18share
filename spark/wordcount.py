lines = spark.read.text("gutenberg")
#lines.show()

words = lines.select("value")
words.show()
