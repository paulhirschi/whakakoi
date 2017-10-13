---
## Required API Routes

[x] /api                              GET               Welcome message
/api/authenticate                 POST              Authenticate with email/password
/api/me                           GET               Info about current user

/api/invitations                  GET               Get all invites
/api/invitations                  POST              Create an invite
/api/invitations/:email           GET               Get a single invite by email
/api/invitations/:email           DELETE            Delete a single invite

/api/users                        GET               Get all the users
/api/users                        POST              Create a user
/api/users/:email                 GET               Get a single user
/api/users/:email                 PUT               Update a user with new info
/api/users/:email                 DELETE            Delete a user

/api/dailywod                     GET               Get all daily WODs
/api/dailywod                     POST              Create a daily WOD
/api/dailywod/:date               GET               Get a single Daily WOD
/api/dailywod/:date               PUT               Update a Daily WOD with new info
/api/dailywod/:date               DELETE            Delete a Daily Wod

/api/customwods                   GET               Get all custom WODs
/api/customwods                   POST              Create a custom WOD
/api/customwods/:email/:name      GET               Get a single custom WOD
/api/customwods/:email/:name      PUT               Update a custom WOD with new info
/api/customwods/:email/:name      DELETE            Delete a custom WOD

/api/namedwods                    GET               Get all named WODs
/api/namedwods                    POST              Create a named WOD
/api/namedwods/:name              GET               Get a single named WOD
/api/namedwods/:name              PUT               Update a named WOD with new info
/api/namedwods/:name              DELETE            Delete a single named WOD

/api/messages                     GET               Get all messages
/api/messages                     POST              Create a message

---
# Response Messages
* All response messages will follow this format
{
  success: true,
  message: success,
  data: [dataObject]
}

---
## DB Model Schemas

* Custom Wod Schema
name: String,
score: String,
description: String,
created_by: String

* Daily Wod Schema
description: String,
date: {type: Date, default: Date.now}

* Invitation Schema
email: { type: String, required: true, index: { unique: true }}

* Messages Schema
message: String,
time: {type: Date, default: Date.now},
created_by: String
capped: { size: 1024, max: 50}

* Named Wods Schema
name: {type: String, index: {unique: true}},
type: String,
score: String,
description: String

* User Schema
name: { first: String, last: String },
email: { type: String, required: true, index: { unique: true }},
password: {type: String, required: true, select: false },
battle_cry: String,
custom_wods: Array,
complete_wods: [{name: String, score: String}],
rights: { type: String, default: 'read'},
stats: {
  clean_jerk: [{ date: {type: Date, default: Date.now}, data: String }],
  snatch: [{ date: {type: Date, default: Date.now}, data: String }],
  deadlift: [{ date: {type: Date, default: Date.now}, data: String }],
  front_squat: [{ date: {type: Date, default: Date.now}, data: String }],
  back_squat: [{ date: {type: Date, default: Date.now}, data: String }],
  body_weight: [{ date: {type: Date, default: Date.now}, data: String }],
  bench_press: [{ date: {type: Date, default: Date.now}, data: String }]
}