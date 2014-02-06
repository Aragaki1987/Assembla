#!/bin/sh
echo =========================== Restarting Domain ===================================
asadmin stop-domain
asadmin start-domain
