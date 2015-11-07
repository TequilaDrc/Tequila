CREATE TABLE [IntroduceTag] (
	[Tag] [VARCHAR](1) NOT NULL DEFAULT ('0'),
	CONSTRAINT [PK_IntroduceTag] PRIMARY KEY (Tag)
);

INSERT INTO IntroduceTag (Tag) VALUES ( '0' );