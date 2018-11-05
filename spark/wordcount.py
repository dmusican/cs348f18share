import pyspark.sql.functions as psf

lines = spark.read.text("gutenberg")
#lines.show()

words = lines.select(psf.split("value","\s"))
words.show()
