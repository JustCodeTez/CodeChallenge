# CodeChallenge
Automation code challenge
Hey this is Tejashree....
Started adding my automation code challenges and innovations inside this repository!! 

Checking if a new contributor can be added to the project!!!

Use the below command to check commit log for a given commit message/phrase:

git show `git log --oneline | grep "HP" | cut -d " " -f1`

# git show <commitID> : shows the commit log for a given commit ID
# git log --oneline gives entire Repo's git log in one line from the beginning
# we then pass the output of above command to grep (search for HP in commit message)
# then pass the output to cut command which separates the fields by delimiter (here space character) and then fetch the first field's value
# Note that this only works when we have only one result
# if only the first result needs to be fetched from a list of commit logs, use head -1

# Testing remote branches
