INSERT INTO `roles` (`id`, `name`) VALUES
('8sk27hdk-281j-28da-9hka-719sha82k01s', 'ROLE_ADMIN'),
('9ba045f8-d51b-415a-b5c3-bb287acc72dc', 'ROLE_USER');

INSERT INTO `user` (`id`, `first_name`, `last_name`, `mobile_number`, `password`, `username`) VALUES
('616b3312-375a-45f7-90b8-58759518fef4', 'Admin', 'Admin', '+6281285930030', '$2a$10$QTCw7Evdf.W8j5toryNceOgGwmU4dd1uUr22/n/OXBaFKQNeitQ/W', 'admin'),
('9e99eb96-9b69-4efa-bef5-74d23cf6016c', 'Arka', 'Kharisma', '+6280000000000', '$2a$10$lKCwquyDsXhaucey9GCfsuzQMDCKfOTZEa9TFwEAUH.nJ3DPtSrY.', 'arka@example.com');

INSERT INTO `user_roles` (`user_id`, `role_id`) VALUES
('616b3312-375a-45f7-90b8-58759518fef4', '8sk27hdk-281j-28da-9hka-719sha82k01s'),
('616b3312-375a-45f7-90b8-58759518fef4', '9ba045f8-d51b-415a-b5c3-bb287acc72dc'),
('9e99eb96-9b69-4efa-bef5-74d23cf6016c', '8sk27hdk-281j-28da-9hka-719sha82k01s'),
('9e99eb96-9b69-4efa-bef5-74d23cf6016c', '9ba045f8-d51b-415a-b5c3-bb287acc72dc');

INSERT INTO `agency` (`id`, `code`, `details`, `name`, `owner_id`) VALUES
('f425a8fe-55a9-4a12-9496-190cd079bfaa', 'TRSJKT', 'Transportasi Jakarta', 'Trans Jakarta', '9e99eb96-9b69-4efa-bef5-74d23cf6016c');

INSERT INTO `bus` (`id`, `capacity`, `code`, `make`, `agency_id`) VALUES
('994f8d4c-4128-4e16-a8e1-3629b5df5003', 26, 'K07A', '2014', 'f425a8fe-55a9-4a12-9496-190cd079bfaa'),
('f6fd1a73-86af-459c-abe0-987bee6802f7', 30, 'K08', '2015', 'f425a8fe-55a9-4a12-9496-190cd079bfaa');

INSERT INTO `stop` (`id`, `code`, `detail`, `name`) VALUES
('1eae628c-060e-409c-a57d-0123ceb5ce76', 'PRH01', 'Halte Palmerah', 'Palmerah'),
('24abac39-fb59-488d-a93c-5cb8b331a764', 'LBK01', 'Halte Lebak Bulus', 'Lebak Bulus'),
('2d068769-cc3d-4557-b9fb-67923bd8b9aa', 'PPG01', 'Halte Pondok Pinang', 'Pondok Pinang'),
('4ba9e1ce-407e-40fb-b043-62990a388756', 'FAT01', 'Halte Fatmawati', 'Fatmawati'),
('724a9c79-6f3c-42f5-a7b5-10bc8d79ae74', 'BLM01', 'Halte Blok M', 'Blok M'),
('a8873aef-9849-4282-bcfa-5569ad6354d1', 'KMG01', 'Halte Kemang', 'Kemang'),
('f69ed925-4c31-488c-b3e8-34f1da0b5f5c', 'PID1', 'Halte Pondok Indah', 'Pondok Indah');

INSERT INTO `trip` (`id`, `fare`, `journey_time`, `agency_id`, `bus_id`, `dest_stop_id`, `source_stop_id`) VALUES
('16cb2f9e-b463-4bef-9867-5fc5d172433d', 1, '08.08', 'f425a8fe-55a9-4a12-9496-190cd079bfaa', 'f6fd1a73-86af-459c-abe0-987bee6802f7', 'f69ed925-4c31-488c-b3e8-34f1da0b5f5c', '2d068769-cc3d-4557-b9fb-67923bd8b9aa'),
('4de718c2-8795-43d2-86f9-a3f8b3db3a47', 1, '08.00', 'f425a8fe-55a9-4a12-9496-190cd079bfaa', 'f6fd1a73-86af-459c-abe0-987bee6802f7', '2d068769-cc3d-4557-b9fb-67923bd8b9aa', '24abac39-fb59-488d-a93c-5cb8b331a764');

INSERT INTO `trip_schedule` (`id`, `available_seats`, `trip_date`, `trip_detail_id`) VALUES
('81f99e6d-2b24-42d5-aa85-5374f750fb70', 25, '2021-05-04', '16cb2f9e-b463-4bef-9867-5fc5d172433d');

INSERT INTO `ticket` (`id`, `cancellable`, `journey_date`, `seat_number`, `passenger_id`, `trip_schedule_id`) VALUES
('3886891c-a0f1-4434-89de-27a9e89d96fb', b'0', '2021-05-04', 1, '9e99eb96-9b69-4efa-bef5-74d23cf6016c', '81f99e6d-2b24-42d5-aa85-5374f750fb70');