public class findValue {

    public static void main(String[] args) {
        String input = "[args={},config=com.github.dockerjava.api.model.ContainerConfig@5f453412[attachStderr=false,attachStdin=false,attachStdout=false,cmd=<null>,domainName=,entrypoint={/docker-entrypoint.sh},env={PATH=/usr/local/sbin:/usr/local/bin:/usr/sbin:/usr/bin:/sbin:/bin},exposedPorts=com.github.dockerjava.api.model.ExposedPorts@14426256,hostName=96072073ff86,image=testcontainers/bpufrd1wbvn8qkzn,labels={description=Setting up a rsh server for IT, org.testcontainers=true, org.testcontainers.sessionId=6f7b7e72-b7cd-4953-9a67-e2757fee4b4c, owner=Ian, version=1.0},macAddress=<null>,networkDisabled=<null>,onBuild=<null>,stdinOpen=false,portSpecs=<null>,stdInOnce=false,tty=false,user=,volumes=<null>,workingDir=,healthCheck=<null>],created=2019-10-14T02:53:21.875979324Z,driver=overlay2,execDriver=<null>,hostConfig=com.github.dockerjava.api.model.HostConfig@419fc1d6[binds=com.github.dockerjava.api.model.Binds@46c85b61,blkioWeight=0,blkioWeightDevice=<null>,blkioDeviceReadBps=<null>,blkioDeviceWriteBps=<null>,blkioDeviceReadIOps=<null>,blkioDeviceWriteIOps=<null>,memorySwappiness=<null>,nanoCPUs=<null>,capAdd=<null>,capDrop=<null>,containerIDFile=,cpuPeriod=0,cpuRealtimePeriod=0,cpuRealtimeRuntime=0,cpuShares=0,cpuQuota=0,cpusetCpus=,cpusetMems=,devices=<null>,deviceCgroupRules=<null>,diskQuota=<null>,dns=<null>,dnsOptions=<null>,dnsSearch=<null>,extraHosts={},groupAdd=<null>,ipcMode=private,cgroup=,links=<null>,logConfig=com.github.dockerjava.api.model.LogConfig@192800d,lxcConf=<null>,memory=0,memorySwap=0,memoryReservation=0,kernelMemory=0,networkMode=default,oomKillDisable=false,init=<null>,autoRemove=false,oomScoreAdj=0,portBindings={},privileged=false,publishAllPorts=true,readonlyRootfs=false,restartPolicy=no,ulimits=<null>,cpuCount=0,cpuPercent=0,ioMaximumIOps=0,ioMaximumBandwidth=0,volumesFrom={},mounts=<null>,pidMode=,isolation=<null>,securityOpts=<null>,storageOpt=<null>,cgroupParent=,volumeDriver=,shmSize=67108864,pidsLimit=<null>,runtime=runc,tmpFs=<null>,utSMode=,usernsMode=,sysctls=<null>,consoleSize=[0, 0]],hostnamePath=/var/lib/docker/containers/96072073ff86d2395f6fcc4c92be66396f3b4658e7c4c737dec6c269185c926a/hostname,hostsPath=/var/lib/docker/containers/96072073ff86d2395f6fcc4c92be66396f3b4658e7c4c737dec6c269185c926a/hosts,logPath=/var/lib/docker/containers/96072073ff86d2395f6fcc4c92be66396f3b4658e7c4c737dec6c269185c926a/96072073ff86d2395f6fcc4c92be66396f3b4658e7c4c737dec6c269185c926a-json.log,id=96072073ff86d2395f6fcc4c92be66396f3b4658e7c4c737dec6c269185c926a,sizeRootFs=<null>,imageId=sha256:6eba8202d32bd1023791242e82cbabbc0c6a7c969ca40f0f3a123fc336bb9ae1,mountLabel=,name=/hopeful_wright,restartCount=0,networkSettings=com.github.dockerjava.api.model.NetworkSettings@7cc7769d[bridge=,sandboxId=271006ada4c8c37f015868bc940116b4450e02aa278b9a9afa06cd82148a64df,hairpinMode=false,linkLocalIPv6Address=,linkLocalIPv6PrefixLen=0,ports={514/tcp=[Lcom.github.dockerjava.api.model.Ports$Binding;@34f636a5},sandboxKey=/var/run/docker/netns/271006ada4c8,secondaryIPAddresses=<null>,secondaryIPv6Addresses=<null>,endpointID=17e070ff6bfdd2d29899eb1be98276ec9bb0bf8afb535ba1e3f9b4880883e982,gateway=192.168.70.1,portMapping=<null>,globalIPv6Address=,globalIPv6PrefixLen=0,ipAddress=192.168.70.3,ipPrefixLen=23,ipV6Gateway=,macAddress=02:42:c0:a8:46:03,networks={bridge=com.github.dockerjava.api.model.ContainerNetwork@5d55eb7a[ipamConfig=<null>,links=<null>,aliases=<null>,networkID=ee69cf13baee8ce148a1247f25f5d8aa6d0dc284780c576f35e3dc332d3ada36,endpointId=17e070ff6bfdd2d29899eb1be98276ec9bb0bf8afb535ba1e3f9b4880883e982,gateway=192.168.70.1,ipAddress=192.168.70.3,ipPrefixLen=23,ipV6Gateway=,globalIPv6Address=,globalIPv6PrefixLen=0,macAddress=02:42:c0:a8:46:03]}],path=/docker-entrypoint.sh,processLabel=,resolvConfPath=/var/lib/docker/containers/96072073ff86d2395f6fcc4c92be66396f3b4658e7c4c737dec6c269185c926a/resolv.conf,execIds=<null>,state=com.github.dockerjava.api.command.InspectContainerResponse$ContainerState@1ae11dd3[status=running,running=true,paused=false,restarting=false,oomKilled=false,dead=false,pid=5109,exitCode=0,error=,startedAt=2019-10-14T02:53:24.269405173Z,finishedAt=0001-01-01T00:00:00Z,health=<null>],volumes=<null>,volumesRW=<null>,node=<null>,mounts=[],graphDriver=com.github.dockerjava.api.command.GraphDriver@79ae2d13[name=overlay2,data=com.github.dockerjava.api.command.GraphData@7ce87f9d[rootDir=<null>,deviceId=<null>,deviceName=<null>,deviceSize=<null>,dir=<null>]],platform=linux]\n";

        System.out.println(input.indexOf("ipAddress"));
        System.out.println(input.indexOf("=", input.indexOf("ipAddress") ));
        System.out.println(input.indexOf(",", input.indexOf("ipAddress") ));
        System.out.println(input.substring(input.indexOf("=", input.indexOf("ipAddress") )+1, input.indexOf(",", input.indexOf("ipAddress") )));

        System.out.println("=========");

        System.out.println(new findValue().getContainerInternalIp(input));
    }

    private String getContainerInternalIp(String containerInfo) {
        int ipaAddressIdx = containerInfo.indexOf("ipAddress");
        int nextEqualIdx = containerInfo.indexOf("=", ipaAddressIdx);
        int nextCommaIdx = containerInfo.indexOf(",", ipaAddressIdx);
        return containerInfo.substring(nextEqualIdx+1, nextCommaIdx);
    }
}
