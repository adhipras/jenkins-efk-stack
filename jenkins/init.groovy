import jenkins.model.*
import hudson.security.*

def env = System.getenv()

def jenkins = Jenkins.getInstance()
if(!(jenkins.getSecurityRealm() instanceof HudsonPrivateSecurityRealm))
    jenkins.setSecurityRealm(new HudsonPrivateSecurityRealm(false))

if(!(jenkins.getAuthorizationStrategy() instanceof GlobalMatrixAuthorizationStrategy))
    jenkins.setAuthorizationStrategy(new GlobalMatrixAuthorizationStrategy())

// Create new Jenkins user account.
// Username and password are from environment variables.
def user = jenkins.getSecurityRealm().createAccount(env.JENKINS_USERNAME, env.JENKINS_PASSWORD)
user.save()
jenkins.getAuthorizationStrategy().add(Jenkins.ADMINISTER, env.JENKINS_USERNAME)

jenkins.save()