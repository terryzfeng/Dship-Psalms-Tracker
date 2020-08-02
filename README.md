# Psalms Daily Bible Tracker
For Dship, to keep track of what chapter of Psalms we are on each day, 
reading a chapter a day, and to pull the daily Psalm chapter in KJV.

Start Date: 2020-06-17 (Day 1, Psalms 1)

End Date: 2020-11-13 (Day 150, Psalms 150)

## Documentation
Uses public domain KJV Bible from [https://bible-api.com](https://bible-api.com)

See the github repo [here](https://github.com/seven1m/bible_api)

## How to run
### 1. Clone the repository
   In terminal: `git clone https://github.com/terryzfeng/Dship-Psalms-Tracker.git`
### 2. Download dependencies
   - JSON.simple [https://storage.googleapis.com/google-code-archive-downloads/v2/code.google.com/json-simple/json-simple-1.1.1.jar](https://storage.googleapis.com/google-code-archive-downloads/v2/code.google.com/json-simple/json-simple-1.1.1.jar)

### 3. Compile and Run

   In terminal, simply use the command
    
   `make`
    
   or in terminal
    
    javac -classpath json-simple-1.1.1.jar Psalms.java
   
    java -cp .:json-simple-1.1.1.jar Psalms
    
   or run in IDE by adding JSON.simple to CLASSPATH

&ndash; terry feng
