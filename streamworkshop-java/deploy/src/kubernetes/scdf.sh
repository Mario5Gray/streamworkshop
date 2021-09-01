export scdf_namespace="default"
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

