package spricedmeritoroutbound.Service;

import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.Session;
import com.jcraft.jsch.SftpATTRS;

import spricedmeritoroutbound.Entities.AuditRecord.Status;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SftpClient {
	
	private static final Logger log = LoggerFactory.getLogger(SftpClient.class);

    private String host;
    private int port;
    private String username;
    private String privateKeyFile;
    private String passphrase;

    public SftpClient(String host, String sftpPort, String username, String privateKeyFile, String passphrase) {
        this.host = host;
        this.port = Integer.parseInt(sftpPort);  // Ensure the port is parsed as an integer
        this.username = username;
        this.privateKeyFile = privateKeyFile;
        this.passphrase = passphrase;
    }

    public boolean fileExists(String remotePath) throws Exception {
        ChannelSftp sftpChannel = null;
        try {
            sftpChannel = openSftpChannel();
//            log.info("Checking file existence at path: " + remotePath);
            SftpATTRS attrs = sftpChannel.stat(remotePath);
            return attrs != null;
        } catch (com.jcraft.jsch.SftpException e) {
            if (e.id == ChannelSftp.SSH_FX_NO_SUCH_FILE) {
            	log.warn("File not found at path: " + remotePath);
                return false; // File does not exist
            } else {
                throw new Exception("Error checking file existence: " + e.getMessage(), e);
            }
        } finally {
            if (sftpChannel != null) {
                sftpChannel.disconnect();
            }
        }
    }

    private ChannelSftp openSftpChannel() throws Exception {
        JSch jsch = new JSch();
        jsch.setKnownHosts("C:\\Users\\srinath.venugopal_si\\.ssh\\known_hosts");  // Path to known hosts or use default
        jsch.addIdentity(privateKeyFile, passphrase);

        Session session = jsch.getSession(username, host, port);
        session.connect();

        ChannelSftp sftpChannel = (ChannelSftp) session.openChannel("sftp");
        sftpChannel.connect();
        
        return sftpChannel;
    }
    
    
    public Status fileStatus(String remotePath) throws Exception {
        try {
            ChannelSftp sftpChannel = openSftpChannel();
            SftpATTRS attrs = sftpChannel.stat(remotePath);
            sftpChannel.disconnect();

            if (attrs != null) {
                return Status.DONE;  // File exists
            } else {
                return Status.FAILED;  // File does not exist
            }
        } catch (com.jcraft.jsch.SftpException e) {
            if (e.id == ChannelSftp.SSH_FX_NO_SUCH_FILE) {
                return Status.FAILED;  // File does not exist
            } else {
                throw new Exception("Error checking file status: " + e.getMessage(), e);
            }
        }
    }

}
