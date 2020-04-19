FROM confluentinc/cp-schema-registry:5.1.4

COPY publish-events.sh wait-for-it.sh /usr/local/bin/
RUN ["chmod", "+x", "/usr/local/bin/wait-for-it.sh"]
RUN ["chmod", "+x", "/usr/local/bin/publish-events.sh"]
USER root

ENTRYPOINT ["/usr/local/bin/publish-events.sh"]
