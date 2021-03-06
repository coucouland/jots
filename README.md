# Jot - a graph-based data capture platform

[Knowledge Management]: https://en.wikipedia.org/wiki/Knowledge_management
[Fluent interface]: https://en.wikipedia.org/wiki/Fluent_interface

[Introduction]: #introduction

## Introduction

Jot is a [Knowledge Management] tool for capturing complex relationships between information and entities. The management of 
information is becoming increasing complex and this tool aims to provide a way to simplify and manage the relationships between 
ouselves and the data we should be concerned with.

### Concepts

The elements of a Jot graph are based on two primary node types, Jot and Entity, and the relationships between them. As a 
general rule, relationships only exist between Jots and Entities. This helps to reduce complexity and enforce consistent
data modelling practices.

#### Entity

An Entity represents a subject with which a Jot can form a relationship. This could be a person, project, property, or any 
"thing" that might be the subject of information capture.

| Entity       | Description                                                                  |
|--------------|------------------------------------------------------------------------------|
| User         | Represents a user of the Jot application                                     |
| Person       | Used to capture details such as contact information, etc.                    |
| Organization | Represents a business, charity, vendor, etc.                                 |
| Project      | A grouping construct used to organise information captured in Jots           |
| Place        | A location-based entity such as a building, room, attraction, etc.           |
| Asset        | Represents an asset that is typically owned by an individual or organisation |
| Label        | An entity used to decorate the appearance of tagged Jots                     |
| Status       | Indicates the lifecycle status of a Jot                                      |


#### Jot

A Jot is the information itself, and supports various properties relevant to the type of information being captured. The 
simplest Jot - a `TextNote` - supports a title (optional), and text body. Additional properties such as creation date, last 
updated, etc. are automatically captured behind the scenes.

| Jot        | Description                                                                                |
|------------|--------------------------------------------------------------------------------------------|
| TextNote   | Captures textual information in the form of a title (optional) and text body               |
| Event      | A time-based Jot that captures start/end time, duration, recurrence, etc.                  |
| Task       | A lifecycle-based Jot that records the status, assignee, etc.                              |
| Meeting    | An Event sub-type that captures additional information regarding attendees, location, etc. |
| Minutes    | Capture attendees, discussions and action items resulting from a meeting                   |
| Poll       | Record votes against a list of Jots                                                        |
| Attachment | Uploaded content with associated metadata (content type, annotation, etc.)                 |


#### Relationships

The true power of Jot is in the relationships that may be formed between Jots and Entities. The simple `TextNote` will 
automatically record an "authored by" relatioship with the user that created it. Additional relationships with the `Label`
and `Project` entities make it easy to expand the capabilities to include tagging and grouping of notes.

With just two more relationships with a `User` and `Status` entity we now have the features of a basic ticketing tool.

| Jot        | Relationship | Entity       |
|------------|--------------|--------------|
| TextNote   | authored by  | User         |
| Meeting    | organised by | User         |
| Meeting    | attended by  | Person       |
| Event      | located at   | Place        |
| Minutes    | recorded by  | Person       |
| Task       | assigned to  | Person       |
| Attachment | uploaded by  | User         |
| TextNote   | tagged with  | Label        |
| Asset      | owned by     | Organization |
| Task       | part of      | Project      |
| Meeting    | status of    | Status       |

## Jot Flow

The Jot API is designed to imitate the natural flow of information as processed by the human brain. Jot provides a [Fluent interface] that hides the underlying implementation in order to focus on the capture of the information itself.

Some of the key principles of the Jot Flow design include:

* Provide an API designed primarily for information capture. Retrieval and deletion are a lesser priority;
* No distinction between create and update. If record doesn't exist it is created, otherwise it is updated. This includes relationships;
* All records are identifiable by a string, so adding relationships doesn't require special types or data lookup.

### Examples

The following examples demonstrate how Jot Flow imitates natural language for capturing information:

| Description | Jot Flow |
|-------------|----------|
| A text note labeled with "ideas", authored by "Joe Bloggs" | textNote("Example Note").labeledWith("ideas").authoredBy("Joe Bloggs") |
| An invoice with attachment "invoice.pdf", labeled with "Bills" | invoice("Invoice 1").withAttachment("invoice.pdf).labeledWith("Bills") |


===========

A service facilitating creation of jots in space and time

# Metadata
The following attributes help to define a jot

## Content
* Summary
* Description

## Time
* Start/end date
* Recurrence rules
* Date created
* Last modified

## Location (global)
* Lat/long
* Altitude
* Direction

## Location (relative to parent)
* deltaX, deltaY, deltaZ
