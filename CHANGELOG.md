# Sonora API 1.0.1

Minecraft 1.21.1 · NeoForge 21.1.233 · Java 21

## 日本語

- MP3 の上限を **1 曲 60 分、144 MiB** に拡張しました。60 分ちょうどの曲も登録できます。320 kbps のステレオ MP3 なら、60 分で約 137 MiB です。
- ワールド保存曲の容量枠は合計 320 MiB です。MOD 同梱曲にも別枠で合計 320 MiB を使用できます。既存の保存曲と曲 ID はそのまま引き継ぎます。
- 曲の長さを MP3 フレームから計算します。対応する Xing/Info タグのある曲ではエンコーダーが加えた無音を考慮し、タグのフレーム数を実データと照合します。
- 標準アップロードは受領確認を待ちながら複数の小さいパケットを送ります。1 tick の送信上限は 128 KiB、確認待ちの上限は 512 KiB です。サーバーは公開前にバックグラウンドで曲全体をデコード検証します。

サーバーとクライアントの両方で Sonora を 1.0.1 に更新してください。対応版の Villager House で既存設定が 1800 秒の場合は、クライアントとサーバーの `config/villagerhouse-common.toml` で `maxMp3DurationSeconds = 3600` を指定すると 1 時間まで使用できます。

## English

- MP3 tracks can be **up to 60 minutes and 144 MiB**, including exactly one hour. A 60-minute stereo MP3 at 320 kbps uses approximately 137 MiB.
- World tracks share a 320 MiB budget. Bundled tracks have their own 320 MiB budget. Existing world files keep their track IDs.
- Durations are calculated from the MP3 frames. For supported Xing/Info tags, Sonora checks the frame count against the file and accounts for encoder delay and padding.
- The built-in uploader can send multiple small packets while acknowledgements are in flight, with limits of 128 KiB per tick and 512 KiB outstanding. The server validates the complete MP3 on a worker before publishing it.

Install 1.0.1 on the server and every client. In the matching Villager House build, players upgrading with the previous 1800-second setting can enable one-hour tracks by setting `maxMp3DurationSeconds = 3600` in `config/villagerhouse-common.toml` on the client and server.

## 検証結果 / Performance

Windows 上の同じ PC で専用サーバーとクライアントを動かし、48 kHz ステレオの 60 分 MP3 を使用しました。登録時間には初回転送とサーバーのデコード検証を含みます。

The dedicated server and client ran on the same Windows PC using one-hour, 48 kHz stereo MP3s. Registration includes the first upload and server-side validation. Internet transfer times depend on the connection.

| 実測項目 / Measurement | 結果 / Result |
| --- | ---: |
| VH Music Memory registration, 320 kbps (137.3 MiB) | 80.5 s |
| VH play request to first client audio output | 140 ms |
| Sonora built-in upload and registration, 128 kbps (54.9 MiB) | 29.7 s |
| Seek to 59:57 and resume output, Sonora default 1 s prebuffer | 1.02 s |
| Server tick interval during the run, 95th percentile | 51.2 ms |

320 kbps の曲全体を 9,376 ブロックに分けたデコード試験は 14.2 秒で完了しました。実時間の再生試験は冒頭と末尾で行い、音声出力・シーク後の自然終了を確認しました。上限を 1 サンプル超えた曲は拒否され、1.0.0 の保存曲は同じ ID で読み込まれました。

A separate decoding benchmark processed all 9,376 streaming blocks of the 320 kbps track in 14.2 s. The real-time test exercised the opening and final seconds, including natural completion after seeking. A file one sample over the duration limit was rejected, and an existing 1.0.0 world track reloaded under its original ID.
