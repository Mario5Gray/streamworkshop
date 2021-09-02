sudo systemctl start docker
docker ps
sudo kind create cluster  --config k8-1wnode.yaml
sudo cp -r /root/.kube /$HOME/.kube
sudo chown -R $USER $HOME/.kube