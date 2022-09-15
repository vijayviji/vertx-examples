# Point-to-point
* One or more Producers send msgs to an address, where one or more consumer receives.
* If more than one consumer receive msg, then the msgs are load-balanced across consumers in round-robin.
* A single msg will "not" reach multiple consumers, and will reach only one consumer.

# Request / reply
* This is a special case of point-to-point wherein consumer replies as well.

# Publish / Subscribe
* One or more Producers send msgs to an address which are received by one or more consumers.
* All msgs will be received by all consumers.