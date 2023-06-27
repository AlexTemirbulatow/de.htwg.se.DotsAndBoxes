#
# --Note
#
# build image with: docker build -t dotsandboxes-image .
# run docker container with: docker run -t dotsandboxes-image
#
# To emulate the GUI properly make sure XMing(X Server) is installed and running.
#


# base image for scala and sbt
FROM hseeberger/scala-sbt:17.0.2_1.6.2_3.1.1

# install dependencies for graphical use
RUN apt-get update && apt-get install -y libxrender1 libxtst6 libxi6 \
    libgl1-mesa-glx libgtk-3-0 openjfx libgl1-mesa-dri libgl1-mesa-dev \ 
    libcanberra-gtk-module libcanberra-gtk3-module default-jdk

# containers display server connects to X server
ENV DISPLAY=host.docker.internal:0.0

# set directory inside container
WORKDIR /dotsandboxes
# copy content into directory
ADD . /dotsandboxes

# run application
CMD sbt run
