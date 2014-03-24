hocrinfoaggregator
==================

HocrInfoAggregator

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

  polytonic greek hyphenation patterns for Apache FOP based on LaTeX (binary and xml)

* src/main/resources/lucene-grc
  
  Lucene Greek spell checker binary input data 
