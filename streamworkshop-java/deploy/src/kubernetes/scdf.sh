export scdf_namespace="workshop"
export scdf_release="lab_scdf"

export prometheus_namespace="prometheus"
export prometheus_release="lab_prometheus"

helm repo add bitnami https://charts.bitnami.com/bitnami
helm install -n ${scdf_namespace} ${scdf_release} bitnami/spring-cloud-dataflow

if ! helm status "${scdf_release}" >/dev/null; then
  echo "Install bitnami/spring-cloud-dataflow scdf_release_name=${scdf_release} scdf_namespace=${scdf_namespace}"

  helm upgrade --wait -n "${scdf_namespace}" --install "${scdf_release}" bitnami/spring-cloud-dataflow \
    --set server.configuration.batchEnabled=false \
    --set server.configuration.streamingEnabled=true \
    --set skipper.enabled=true \
    --set rabbitmq.enabled=true \
    --set rabbitmq.enabled=true
fi

export SERVICEPORT=$(kubectl get --namespace default -o jsonpath="{.spec.ports[0].port}" services my-release-spring-cloud-dataflow-server)
kubectl port-forward --namespace default svc/my-release-spring-cloud-dataflow-server ${SERVICEPORT}:${SERVICEPORT} & echo "http://127.0.0.1:${SERVICEPORT}/dashboard" &
