

CREATE TABLE `nasdaq_prices` (
 `ticker` varchar(4),
 `date` int(8),
 `open` decimal(4,2),
 `high` decimal(4,2),
 `low` decimal(3,1),
 `close` decimal(4,2),
 `vol` int(6)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
