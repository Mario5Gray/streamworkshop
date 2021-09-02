helm install mongodb bitnami/mongodb \
  --set auth.rootPassword=rootpassword \
  --set auth.database=stocks \
  --set auth.username=user \
  --set auth.password=password