# e2j-name
Convert English to Katakana. This application used CMU speech library to convert English to Phonetic alphabet also used Antlr to translate Phonetic alphabet to Katakana. The quality is really low and currently it does not support 'ッ'.

## Requirements
+ Java 1.8
+ Gradle 2.5

## Build
``gradle clean fatCapsule``

## Run
``java -jar build/libs/e2j-name-1.0-capsule.jar james``

``ジェイムズ``