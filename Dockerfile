FROM ubuntu:22.04

# Install basic utilities and Python 3 pip
RUN apt-get update -y && \
    apt-get install -y wget && \
    apt-get install python3-pip -y

# Install Miniforge for ARM64
RUN wget https://github.com/conda-forge/miniforge/releases/latest/download/Miniforge3-Linux-aarch64.sh && \
    bash Miniforge3-Linux-aarch64.sh -b -p /miniconda && \
    rm Miniforge3-Linux-aarch64.sh

# Set PATH to include conda
ENV PATH="/miniconda/bin:${PATH}"

# Install Python libraries using Conda
RUN conda install -c conda-forge flask pandas numpy matplotlib scikit-learn seaborn joblib scipy -y && \
    conda install -c pytorch pytorch -y && \
    conda install -c huggingface transformers==4.35.2 -y && \
    conda install -c conda-forge spacy==3.6.1 -y

# Install Gunicorn and Gevent
RUN pip install gunicorn gevent

# Copy the rest of your application's code
COPY . /opt/

WORKDIR /opt/

# Expose the port the app runs on
EXPOSE 5000


CMD ["python3", "app.py"]

