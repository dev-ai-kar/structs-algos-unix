# This script starts the IntelliJ IDEA IDE in the background.
# The output is redirected to /dev/null to prevent it from being displayed in the terminal.
# The script uses the nohup command to ensure that the process continues running even if the terminal is closed.
# The path to the IntelliJ IDEA executable is specified as /opt/idea-IC-242.26775.15/bin/idea.
nohup /opt/idea-IC-242.26775.15/bin/idea >/dev/null 2>&1 &

# run `idea-detached` to start the IDE in detached mode
