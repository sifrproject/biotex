# Sample configuration

```properties
#Source patterns directory
biotex.source_patterns = patterns

biotex.source_dataset_reference = dataSetReference

biotex.source_stop_words = stopWords
biotex.source_treetagger =/path/to/TreeTagger


biotex.output=output

biotex.input_file=corpus.txt


#(all|multi)
biotex.type_of_terms=all

#french, english or spanish
biotex.language=french

biotex.min_term_frequency=1

#Default: 200
biotex.number_of_patterns=200

#For one document       :   LValue     CValue
# For a set of documents :   LIDFValue  F-OCapi_A   F-OCapi_M   F-OCapi_S   F-TFIDF-C_A     F-TFIDF-C_M     F-TFIDF-C_S
#                            TFIDF_A     TFIDF_M     TFIDF_S     Okapi_A     Okapi_M     Okapi_S
biotex.measure=Okapi_A

#1 = single file (only for LValue  or CValue)
#2 = set of files (for LIDF-value or any measure)
biotex.source_type=2
```

