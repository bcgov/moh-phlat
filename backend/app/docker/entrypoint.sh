#!/bin/sh

# If ADDRESS_DOCTOR_KEYSTORE env var exists, decode it to /tmp/addrval-temp.jks
if [ -n "$ADDRESS_DOCTOR_KEYSTORE" ]; then
  echo "Decoding ADDRESS_DOCTOR_KEYSTORE to /tmp/addrval-temp.jks" >&2
  # Use printf to preserve the full variable (safer than echo) and decode base64
  printf '%s' "$ADDRESS_DOCTOR_KEYSTORE" | base64 -d > /tmp/addrval-temp.jks
  chmod 640 /tmp/addrval-temp.jks || true
else
  echo "WARNING: ADDRESS_DOCTOR_KEYSTORE environment variable not set; skipping keystore creation" >&2
fi

# exec the Java process so signals are forwarded correctly
exec java -XX:MaxRAMPercentage=80.0 -jar /app/target/phlat-backend.jar