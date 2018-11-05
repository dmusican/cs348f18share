import pyspark.sql.functions as psf

#lines = spark.read.text("gutenberg")
lines = spark.read.text("/Accounts/dmusicant/Downloads/gutenberg/gutenberg/*/b*")
#lines.show()

words1 = lines.select(psf.input_file_name(), \
                      psf.split("value","\s")) \
              .toDF("filename", "wordlist")

words2 = words1.select(psf.explode("wordlist")) \
              .toDF("word")

results = words2.groupBy("word") \
                .agg(psf.count("word")) \
                .toDF("word","thecount")

#results.show()

results.repartition(1).write.save("output", format="csv")
