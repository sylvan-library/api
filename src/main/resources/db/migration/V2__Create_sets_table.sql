create table sets (
    Id serial primary key,
    Name varchar(255) not null,
    Abbr varchar(6) not null,
    ReleaseDate date,
    IconUrl varchar(500),
    PromoArtUrl varchar(500)
)