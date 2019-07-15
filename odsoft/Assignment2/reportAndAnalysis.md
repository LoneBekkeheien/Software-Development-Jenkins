# Analysis and Evaluation Report

## Component 2

In this component the task is to implement the Jenkins pipeline using either the "Promoted Build" or "Build Pipeline" plugin, and to execute the pipeline with a parallel build. The "Promoted Build" plugin was chosen, but was accompanied by other plugins as well, such as the "Multijob" plugin, in order to meet the requirements that were given (with heavy regard to the requirement that: "the overall "build" should fail if any of the stages fail"). The promoted build plugin was chosen over the build pipeline plugin because it allows for manual confirmation to be implemented in an easier fashion. The Multijob plugin was used because neither of the two main plugins had a feature where one job would wait for a group of jobs to finish, which is highly needed with a parallel build approach.

This "pipeline" has as many jobs in parallel as possible at a time, in this case that means that the Multijob job "BuildMulti" has four phases, where all jobs within a stage run in parallel, but the phases are sequential. First we need to checkout and build the repository, which cannot be done in parallel with anything else, since all other jobs are dependent on this to be completed. In the next phase the jobs: javadoc, unitTests and integrationTests are run in parallel. After this we have systemTest and mutationTest together in one phase, and lastly email and userConfirmation in the final phase. The Tag job is outside of "BuildMulti" because it should run whether or not the build fails.


Below are the results of and reasoning for all the Jenkin's jobs that make up the Pipeline.
The given times are averaged.
Each job has its own section which is built up as follows:

* A description of the job with its fundamental information and justification for choosing this approach (analysis).  
* The execution time of this stage
* The percentage of the total build time: (total time of stage)/(total time)

### BuidlMulti
* This is the "Multijob" job, with all the other jobs in it as "multjob phases", except for the job that pushes tags to Bitbucket. In essence this contains the entire pipeline.
* Job time = 3min 58sec
* Percentage of total build time: 238s / (252s) = 94.44%

### Checkout+Build+War
* This job has git selected under SCM, and is linked to this repository. This way we checkout the repository automatically. Furthermore it runs "gradle build" and "gradle war". The reason that gradle war is included in this job is because Jenkins only lets you archive artefacts from the job's own workspace. If we had gradle war in its own job we would need to link to the repository again and checkout, which is not very efficient.
* Job time = 5.7s
* Percentage of total build time: 5.7s / (252s) = 2.26%

### Javadoc
* This job builds the javadoc and publishes it in Jenkins. The gradle javadoc task is run in a gradle script and the post build action "Publish Javadoc" is used. This is the simplest and most straightforward way to complete this task using a Jenkins job.
* Job time = 9.5s
* Percentage of total build time: 9.5s / (252s) = 3.77%

### UnitTest
* Executes the Unit Tests, generates a Unit Tests Report, generates a Unit Tests Coverage Report and publishes both reports in Jenkins. Before executing the tests we use the cleanTest command so that we always perform the tests from scratch. This way we know the test are being run correctly. To publish the reports we use the post build action "Publish HTML Reports", which is the simplest way to do just that.
* Job time = 4.1s
* Percentage of total build time: 4.1s / (252s) = 1.63%


#### IntegrationTest
* Executes the Integration Tests, generates an Integration Tests Report, generates an Integration Tests Coverage Report and publishes both reports in Jenkins. Here we use the same approach as in UnitTest, running cleanIntegrationTest, and using the "Publish HTML Reports" post build action.
* 22s
* Percentage of total build time: 22s / (252s) = 8.73%

#### MutationTest
* Executes the Mutation Tests, generates a Mutation Coverage Report and publishes it in Jenkins. Here we simply run a gradle script with "pitest" and use the "Publish HTML Reports" post build action.  
* Job time = 2min 35sec
* Percentage of total build time: 155 / (252s) = 61.51%

#### SystemTest
* Deploys the application (.war file) to a pre-configured Tomcat Server instance and performs a smoke test using curl. This is done via two lines of shell script. One can also use the build action "Copy artefacts from another project", but since we need the shell script to run the curl command anyway, I found this to be a better approach.
* Job time = 0.13s
* Percentage of total build time: 0.13s / (252s) = 0.05%

#### Email
* Sends an Email to the user (defined in the recipient list) when all previous jobs of the current build have been successful. This is done via the post build action "Editable email notification" and by calling this job from BuildMulti with the predefined parameter BUILD_NUMBER of BuildMulti. This way we can use the build number of the upstream build, the pipeline start, if we also say that Email is a parameterized project in the configuration, choosing a string parameter with the name of the predefined parameter.  
* Job time = 0.42s
* Percentage of total build time: 0.42s / (252s) = 0.17%

#### UserConfirmation
* Waits for the users approval to continue. This is done in order to run a manual acceptance test. The user confirmation is implemented using the promote builds plugin with the "Only when manually approved" criteria selected. Why this method is used and not the one from the "Build Pipeline" plugin is used, was discussed in the beginning of this report.
* Job time = 0.17s
* Percentage of total build time: 0.17s / (252s) = 0.07%

#### Tag
* Creates a tag with current upstream build number (the one of BuildMulti) and status, which is pushed to the repository. This job is actually divided into two; Tag, which is called if the build is successful, and TagFailure, which is called if the build fails. TagFailure is called as a post build action from BuildMulti using  "Trigger parameterized build on other projects", so that we have the upstream build number. Tag is called after the UserConfirmation build has been promoted by manual confirmation using the "Trigger/call builds on other projects" action in the "Promote builds when.." section. Actually creating and pushing the the tags is done with two lines of shell script, after making sure that the job is parameterized with the upstream build number. The Tag jobs also need to be connected to the repository, and in this case using the ssh url in order to be allowed to push.
* Job time = 14s
* Percentage of total build time: 14s / (252s) = 5.56%

### Comment
Since the BuildMulti job takes longer than the sum of its individual jobs (due to wait times, initialization, etc), one has to add the percentage of the BuildMulti job and the Tag job to get 100%.


## Component 3

The Component three along with the other Component assignments forces the students to work with Jenkins and the construct of Pipelines. The Component three especially takes a closer look on sequential builds in Jenkins Pipeline using a Jenkinsfile. The Jenkinsfile uses a scripted pipeline syntax instead of a declarative pipeline syntax because the build for this component should be sequential. With scripted pipeline syntax the stages are built sequentially by nature. In a declarative pipeline syntax does not build the stages sequentially by nature and needs extra code for this to happen. Therefore the scripted pipeline syntax was chosen to simplify the code in the Jenkinsfile.  

### Evaluation of Component three
This report displays the result and more important the evaluation of each and every state of the underlying Pipeline in initialised in the Jenkinsfile.
The given times are averaged.
Each and every Component has its own section which is build up as follows:

* A brief Analysis of the Component
* A small description of the stage with its fundamental information.
* The total time of execution
* Percentage of the execution of the stage: (total time of Stage)/(total time)   

#### Checkout
* There is a possibility to only check out one branch. Since it is a better approach to work on more than one branch the checkout should consider the focused branches.
* This Stage checks out the Git repository for changes and updates the Jenkins Workspace to the currant state. It is implemented using pipeline syntax for checkout.
* 5s
* 5s/86s = 0.058 = 5.8%


#### Build Project
* The build statement indicates only one step. But in this section all the important documents are build and created. Therefore not only the gradle build statement is placed in this stage but also the creation of the war file and the javadoc. This gradle commands are fundamental for future parallel task execution.
* Not only executing the gradle build statement but also creating a war file and the javadoc. Implemented using shell commands.
* 16s
* 16s/86s = 0.186 = 18.6%

#### Archive Files
* Not only archiving the war file like required but also the publishing of the javadoc is a better approach then only doing one step.
* Archiving the war file and publishing the javadoc in Jenkins.
* 0.774s
* 0.774s/86s = 0.009 = 0.9%

#### Unit Tests
* The Unit Test is done by the gradle commands. There is a possibility of doing a clean test which takes a bit longer but gets a proper result and a normal test that relies on already existing data (Cached Data). Publishing the data is a good approach here too.
* Running the Unit Tests and publishing them in Jenkins.
* 6s
* 6s/86s = 0.0698 = 6.98%


#### Integration Test
* Here the approach is similar to the one in Unit Test.
* Running the Integration Tests and publishing them in Jenkins.
* 24s
* 24s/86s = 0.279 = 2.79%

#### Coverage Report
* Using the JaCoCo plugin makes creating coverage reports fast and simple.
* This is done by running jacoco() and using the JaCoCo plugin.
* 2s
* 2s/86s = 0.023 = 2.3%

#### Mutation Test
* We simply run a gradle shell command with "pitest". This stage takes a long time the first time it is run, but any build after the initial one will be much faster. It seems that it might use previous results.
* Running the Mutation Tests and publishing them in Jenkins.
* 2s
* 2s/86s = 0.023 = 2.3%

#### Copy War File to Tomcat
* Not Only doing the required copying but also the smoke test that is connected to the Tomcat server running.
* This steps copies the war File to Tomcat a service used to run Java Web applications on Servlet-Basis. This step also perform a smoke test with a curl command. This approach for the smoketest is chosen because it is easy, little code and accurate. The command returns an error if the Tomcat isn't running.
* 0.472s
* 0.472s/86s = 0.0055 = 0.55%

#### Send Email
* Sending the Email with the success state is quite difficult, therefore there is a local Variable naming the success. It is set either in try or catch block. Also sending the Build number and  the name of the Build as well as the Link to the Build GUI in Jenkins takes the difficulty out and lets the user find and display the Build in Jenkins quickly.   
* Sends an Email to a receiver and displays links to the project for the following confirmation of the user as well
* 2s
* 2s/86s = 0.023 = 2.3%

#### Tag on Bitbucket
* The taging on Git requires the credentials. There are many ways to set those. In this case it was the best approach to set them get the credentials from the OS through some global variables. This had to be fetched at first with a file credential-helper.sh.
* Set the credentials for the Bitbucket access and push a tag to the repository
* 8s
* 8s/86s = 0.093 = 9.3%

#### Confirmation Message
* Using the pipeline method "input" with "message" was a terrific way to implement this.
* This displays a Confirmation Message and waits for the user to confirm. This is done in order to run a manual acceptance test.
* 0.205s
* 0.205s/86s = 0.0028 = 0.28%

## Component 4
The Component four along with the other Component assignments forces the students to work with Jenkins and the construct of Pipelines. The Component 4 especially takes a closer look on parallel builds in Jenkins Pipeline. This indicates that this Component is different then the others and should have a different time to execute all stages or in this case called Components.

### Evaluation of Component
This report displays the result and more important the evaluation of each and every state of the underlying Pipeline initialised in the Jenkinsfile.
The given times are averaged.
Each and every Component has its own section which is build up as follows:

* A brief Analysis of the Component
* A small description of the stage with its fundamental information  
* The total time of execution
* Percentage of the execution of the stage: (total time of Stage)/(total time)   

#### Checkout
* There is a possibility to only check out one branch. Since it is a better approach to work on more than one branch the checkout should consider the focused branches.
* This Stage checks out the Git repository for changes and updates the Jenkins Workspace to the currant state.
* 3s
* 3s/215s = 0.011 = 1.1%


#### Build Project
* The build statement indicates only one step. But in this section all the important documents are build and created. Therefore not only the gradle build statement is placed in this stage but also the creation of the war file and the javadoc. This gradle commands are fundamental for future parallel task execution.
* Not only executing the gradle build statement but also creating a war file and the javadoc.
* 23s
* 23s/215s = 0.107 = 10.7%

#### Archive File
* Not only archiving the war file like required but also the publishing of the javadoc is a better approach then only doing one step.
* Archiving the war file and publishing the javadoc in Jenkins .
* 0.648s
* 0.648s/215s = 0.003 = 0.3%

#### Unit Tests
* The Unit Test is done by the grable commands. There is a possibility of doing a clean test which takes a bit longer but gets a proper result and a normal test that relies on already existing data (Cached Data). Publishing the data is a good approach here too.
* Running the Unit Tests and publishing them in Jenkins.
* 15s
* 15s/215s = 0.067 = 6.7%

#### Coverage Report
*
*
* 1s
* 1s/215s = 0.047 = 4.7%


#### Integration Test
* Here the approach is similar to the one in Unit Test.
* Running the Integration Tests and publishing them in Jankins.
* 9s
* 9s/215s = 0.042 = 4.2%

#### Mutation Test
*
* Running the Mutation Tests and publishing them in Jankins.
* 117s
* 117s/215s = 0.544 = 54.4%

#### Copy War File to Tomcat
* Not Only doing the required copying but also the smoke test that is connected to the Tomcat server running.  
* This steps copies the war File to Tomcat a service used to run Java Web applications on Servlet-Basis.
* 0.506s
* 0.506s/215s = 0.0024 = 0.24%

#### Send Email
* Sending the Email with the success state is quite difficult, therefore there is a local Variable naming the success. It is set either in try or catch block. Also sending the Build number and  the name of the Build as well as the Link to the Build GUI in Jenkins takes the difficulty out and lets the user find and display the Build in Jenkins quickly.     
* Sends an Email to a receiver and displays links to the project for the following confirmation of the user as well
* 1s
* 1s/215s = 0.047 = 4.7%

#### Tag on Bitbucket
* The taging on Git requires the credentials. There are many ways to set those. In this case it was the best approach to set them get the credentials from the OS through some global variables. This had to be fetched at first with a file credential-helper.sh.  
* Set the credentials for the Bitbucket access and push a tag to the repository
* 4s
* 4s/215s = 0.019 = 1.9%

#### Confirmation Message
* The message had to be short and informative.
* This displays a Confirmation Message and waits for the user to confirm. This is done in order to run a manual acceptance test.
* 0.48s
* 0.48s/215s = 0.0022 = 0.22%

## Comparing the performance of the Components

### Total time
* Component 2: 4 minutes, 12 seconds
* Component 3: 1 minute, 26 seconds
* Component 4: 3 minutes, 35 seconds

It is clear to see that Component 3, which is the only component using a sequential build, is the fastest. It is however, somewhat suspicious that the MutationTest stage for Component 3 only takes 2 seconds, when it takes 155 seconds and 117 seconds for Component 2 and 4 respectively. Perhaps this stage uses information about previous results in Component 3, but doesn't for the other two Components. Still, even if this stage for Component 3 had taken 117 seconds, the same amount of time as the only other component which also uses a Jenkinsfile, it would still be the fastest to complete the build. One would maybe assume that running operations in parallel is always faster than running them sequentially, but this isn't always the case, as we can see here. Running in parallel means dividing resources, which are often limited. In Jenkins, running jobs in parallel depends on the available executors and Jenkins workers numbers, and it seems clear from the results that these resources are most efficiently used when performing a sequential build.  
