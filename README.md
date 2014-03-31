hocrinfoaggregator
==================

HocrInfoAggregator

Overview
--------

hocrInfoAggregator's algorithm operates roughly as follows:

1. Create Near-Ground-Truth files from alternative text source
2. Dehyphenates tokens, sets classification and score depending on word type
3. Finds alternative suggestions using a spell checker
4. Rules out suggestions based on properties of polytonic greek
5. Finds single occurrence tokens
6. Aligns working copy with ground truth around single-occurrence tokens
7. If upper case token do not match creating mapping between ngt and
   suggestions
8. Write hOCR containing nlp scores depending on token type. Also add nlp
   scores for possible substitutions.

Building
--------

$ sbt assembly

You'll find a jar under target/scala-2.10/. The sbt build utility can be
retrieved from http://scala-sbt.org.

Running
-------

Add the jar to the class path and run:

$ java eu.himeros.hocr.RunAll src/main/resources $hocr_files

hocrinfoaggregator expects at least a directory containing a .book subdirectory
with hOCR files. The subdirectory must be named
<Author_Name>-<Work_Title>.book. The hOCR files themselves are named
pNNNN.html. It is also possible to supply another version of the same text as a
single XML file in the .book directory. Greek text will be extracted from this
file without regard to annotation.
The final output can be found under
$hocr_file/<Author_Name>-<Work_Title>.book/pNNNN.1.html.

Directories
-----------

* src/main/resources/eu/himeros/resources/dics
  * greek.dic: dictionary of valid greek words
  * syllgreek.dic: dictionary of valid syllabic sequences
  * upgreek.dic: upper case, diacritic-free dictionary

* src/main/resources/eu/himeros/resources/sers
  
  Serialized hash sets created from plain text dicts (see UpperCaseDictMaker.java)

* src/main/resources/eu/himeros/resources/nrm

  Binary, big-endian normalization data for unicode NFC. Only grc2nfcbe.nrm is used though.

* src/main/resources/eu/himeros/transcoders

  Misc. transcoders:

  * beta2u.txt: converts ascii representation of greek into unicode
  * low2up.txt: lower-uppercase mapping of unicode code points, apostrophe and
    commented out OCR mistake fixes, striping diacritics in the process
  * lowercase2uppercase.txt: lowercase-uppercase mapping of unicode code points
    retaining diacritics (is used in reverse mode)
  * monotonic2polytonic.txt: exchanges tonos diacritic for oxia, middle dot for ano teleia
  * ocr2u.txt: adds psili/dasia diacritic to some letter containing the oxia diacritic

* src/main/resources/hyph

  Polytonic greek hyphenation patterns for Apache FOP based on LaTeX (binary and xml)

* src/main/resources/lucene-grc
  
  Lucene Greek spell checker binary input data 
